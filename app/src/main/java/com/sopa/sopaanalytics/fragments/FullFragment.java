package com.sopa.sopaanalytics.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sopa.sopaanalytics.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FullFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FullFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FullFragment extends Fragment {

    private OnFragmentInteractionListener mListener;


    Bitmap bitmap;

    public FullFragment() {
        // Required empty public constructor
    }


    public static FullFragment newInstance() {
        FullFragment fragment = new FullFragment();
        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            byte[] byteArray = getArguments().getByteArray("imageByte");
            bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_full, container, false);

        ImageView imageView = view.findViewById(R.id.imageFull);
        imageView.setImageBitmap(bitmap);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
