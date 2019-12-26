package com.sagun.arshop;

import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.ar.core.Anchor;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {
    private Uri selectedObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);







        ArFragment arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.sceneform_fragment);

        initializeGallery();

        if (arFragment != null) {
            arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {
                if(plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING) {
                    return;
                } else {
                    Anchor anchor = hitResult.createAnchor();

                    placeObject(arFragment, anchor, selectedObject);
                }
            });
        }
    }

    private void initializeGallery() {
        LinearLayout galleryMenu = findViewById(R.id.gallery_menu);

        ImageView chair = new ImageView(this);
        chair.setImageResource(R.drawable.chair_thumb);
        chair.setContentDescription("Chair");
        chair.setOnClickListener(view -> selectedObject = Uri.parse("chair.sfb"));
        galleryMenu.addView(chair);

        ImageView lamp = new ImageView(this);
        lamp.setImageResource(R.drawable.lamp_thumb);
        lamp.setContentDescription("Lamp");
        lamp.setOnClickListener(view -> selectedObject = Uri.parse("lamp.sfb"));
        galleryMenu.addView(lamp);

        ImageView couch = new ImageView(this);
        couch.setImageResource(R.drawable.couch_thumb);
        couch.setContentDescription("Couch");
        couch.setOnClickListener(view -> selectedObject = Uri.parse("couch.sfb"));
        galleryMenu.addView(couch);

        ImageView sofa = new ImageView( this );
        sofa.setImageResource(R.drawable.sofa_thumb);
        sofa.setContentDescription("sofa");
        sofa.setOnClickListener(view -> {selectedObject = Uri.parse("sofa.sfb");});
        galleryMenu.addView(sofa);

        ImageView table = new ImageView( this );
        table.setImageResource(R.drawable.table_thumb);
        table.setContentDescription("table");
        table.setOnClickListener(view -> {selectedObject = Uri.parse("table.sfb");});
        galleryMenu.addView(table);

        ImageView bed = new ImageView( this );
        bed.setImageResource(R.drawable.bed);
        bed.setContentDescription("bed");
        bed.setOnClickListener(view -> {selectedObject = Uri.parse("Bed_01.sfb");});
        galleryMenu.addView(bed);


    }

    private void placeObject(ArFragment fragment, Anchor anchor, Uri model) {
        ModelRenderable.builder()
                .setSource(fragment.getContext(), model)
                .build()
                .thenAccept(modelRenderable -> addNodeToScene(fragment, anchor, modelRenderable))
                .exceptionally((throwable -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(throwable.getMessage())
                            .setTitle("Error");
                    AlertDialog dialog = builder.create();
                    dialog.show();

                    return null;
                }));
    }

    private void addNodeToScene(ArFragment fragment, Anchor anchor, Renderable renderable) {
        AnchorNode anchorNode = new AnchorNode(anchor);
        TransformableNode transformableNode = new TransformableNode(fragment.getTransformationSystem());

        transformableNode.setRenderable(renderable);
        transformableNode.setParent(anchorNode);
        fragment.getArSceneView().getScene().addChild(anchorNode);
        transformableNode.select();
    }



























}
