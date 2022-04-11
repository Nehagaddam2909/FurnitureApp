package com.example.furnitureapp;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.Camera;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.Sun;
import com.google.ar.sceneform.collision.Box;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.BaseArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;
import java.util.List;

public class Sofa extends AppCompatActivity {
    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    private TransformableNode t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sofa);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
    }
    public void model1(View view) {
        setUpModel("sofa.sfb");
        setUpPlane();
    }

    public void model2(View view) {
        setUpModel("sofa2.sfb");
        setUpPlane();
    }
    public void model3(View view) {
        setUpModel("sofa1.sfb");
        setUpPlane();
    }
    public void model4(View view) {
        setUpModel("sofa3.sfb");
        setUpPlane();
    }

    public void setUpModel(String s) {
        ModelRenderable.builder()
                .setSource(this, Uri.parse(s))
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(getApplicationContext(), "Model can't be loaded", Toast.LENGTH_LONG).show();
                    return null;
                });
    }

    public void setUpPlane() {
        arFragment.setOnTapArPlaneListener(new BaseArFragment.OnTapArPlaneListener() {
            @Override
            public void onTapPlane(HitResult hitResult, Plane plane, MotionEvent motionEvent) {
                Anchor anchor = hitResult.createAnchor();
                AnchorNode anchorNode = new AnchorNode(anchor);
                anchorNode.setParent(arFragment.getArSceneView().getScene());
                createModel(anchorNode);
            }
        });
    }


    public void createModel(AnchorNode anchorNode) {
        TransformableNode transformableNode = new TransformableNode(arFragment.getTransformationSystem());

        //transformableNode.setLocalScale(new Vector3(0.25f,0.25f,0.25f));
        transformableNode.setParent(anchorNode);
        transformableNode.setRenderable(modelRenderable);
        transformableNode.select();

        Box b=(Box) transformableNode.getRenderable().getCollisionShape();
        Vector3 v=b.getSize();
        t=transformableNode;
        // Vector3 tra=transformableNode.getWorldScale();
        //Vector3 final1=new Vector3(v.x*tra.x,v.y*tra.y,v.z*tra.z);
        Toast.makeText(getApplicationContext(),v.toString(),Toast.LENGTH_LONG).show();

    }
    public void dimension(View view)
    {

        //  TransformableNode t=(TransformableNode) a.getChildren();;
        Box b=(Box) t.getRenderable().getCollisionShape();
        Vector3 v1=b.getSize();
        Toast.makeText(getApplicationContext(),v1.toString(),Toast.LENGTH_LONG).show();



    }

    public void delete_ar(View view) {
        List<Node> children = new ArrayList<>(arFragment.getArSceneView().getScene().getChildren());
        for (Node node : children) {
            if (node instanceof AnchorNode) {
                if (((AnchorNode) node).getAnchor() != null) {
                    ((AnchorNode) node).getAnchor().detach();
                }
            }
            if (!(node instanceof Camera) && !(node instanceof Sun)) {
                node.setParent(null);
            }
        }
        // removeAnchorNode(a);
    }
}
