package com.sbteecontent.blogspot.inapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sbteecontent.blogspot.inapp.R;
import com.sbteecontent.blogspot.inapp.databinding.FragmentCallsBinding;

import org.jitsi.meet.sdk.JitsiMeet;
import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;


public class CallsFragment extends Fragment {

    public CallsFragment() {
        // Required empty public constructor
    }

    FragmentCallsBinding binding;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCallsBinding.inflate(inflater, container, false);

        URL serverURL;

        try {
            // uses jitsi server for calls
            serverURL = new URL("https://meet.jit.si");
            JitsiMeetConferenceOptions defaultOptions =
                    new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(serverURL)
                            .setWelcomePageEnabled(false)
                            .build();
            JitsiMeet.setDefaultConferenceOptions(defaultOptions);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        // after choosing the meeting-id when user clicks on join button then the call is connected with the provided meeting id
        binding.btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                        .setRoom(binding.meetingCode.getText().toString())
                        .setWelcomePageEnabled(false)
                        .build();

                // launching the live meeting
                JitsiMeetActivity.launch(getContext() , options);

            }
        });


        // Inflate the layout for this fragment
        return binding.getRoot();
    }

}