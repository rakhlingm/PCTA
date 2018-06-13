package team.night.pcta;

import android.app.DialogFragment;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BinFilesListFragment extends DialogFragment {

    Button cancel;
    Button load;
    ListView lv;
    SearchView sv;
    TextView textFileToLoad;
    ArrayAdapter<String> adapter;
    List<String> allFiles; //= {"Lionel Messi","Christiano Ronaldo","Neymar","Gareth Bale"};
    String[] binFiles;
    int radioButtonId;
    RadioButton b;
    String imageFileName;
    int bundle;
    int FW_ID; int major; int minor; int fix;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getAllBinFiles();

        // TODO Auto-generated method stub
        final View rootView=inflater.inflate(R.layout.fragment_bin_files_list, null);

        //SET TITLE DIALOG TITLE
        getDialog().setTitle("Select Image file to load");

        //BUTTON,LISTVIEW,SEARCHVIEW INITIALIZATIONS
        lv=(ListView) rootView.findViewById(R.id.listView1);
        sv=(SearchView) rootView.findViewById(R.id.searchView1);
        cancel =(Button) rootView.findViewById(R.id.dismiss);
        load =(Button) rootView.findViewById(R.id.Load);
        textFileToLoad = (TextView) rootView.findViewById(R.id.textViewFileToLoad);
        textFileToLoad.setSelected(true);

        //CREATE AND SET ADAPTER TO LISTVIEW
        adapter=new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,binFiles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setSelected(true);
                imageFileName = binFiles[position];
                textFileToLoad.setText("     Selected file: " + binFiles[position]);
            }
        });
        final RadioGroup radioGroupBundle = (RadioGroup) rootView.findViewById(R.id.radioGroupBundle);
        radioGroupBundle.clearCheck();
        final RadioGroup radioGroupImage = (RadioGroup) rootView.findViewById(R.id.radioGroupImage);
        radioGroupImage.clearCheck();
        radioGroupBundle.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case -1:{
                        break;
                    }
                    case R.id.radioButtonBundle0:{
                        bundle = 0;
                        break;
                    }
                    case R.id.radioButtonBundle1:{
                        bundle = 1;
                        break;
                    }
                    case R.id.radioButtonBundle2: {
                        bundle = 2;
                        break;
                    }

                    case R.id.radioButtonBT: {
                        bundle = -1;
                        FW_ID = 4;
                        major = 1;
                        minor = 1;
                        fix = 1;
                        radioGroupImage.clearCheck();
                        break;
                    }

                }
            }
        });
        radioGroupImage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButtonId = radioGroupBundle.getCheckedRadioButtonId();
                b = (RadioButton) rootView.findViewById(R.id.radioButtonBT);
                switch (checkedId) {
                    case -1:
                        break;
                    case R.id.radioButtonARM:
                        if(radioButtonId == b.getId()) {
                            radioGroupBundle.clearCheck();
                        }
                        switch (bundle) {
                            case 0: {
                                FW_ID = 0;
                                major = 1;
                                minor = 2;
                                fix = 3;
                                break;
                            }
                            case 1: {
                                FW_ID = 0;
                                major = 7;
                                minor = 8;
                                fix = 9;
                                break;
                            }
                            case 2: {
                                FW_ID = 0;
                                major = 13;
                                minor = 14;
                                fix = 15;
                                break;
                            }
                            case -1: {
                                break;
                            }
                        }

                        break;
                    case R.id.radioButtonDSP:
                        if(radioButtonId == b.getId()) {
                            radioGroupBundle.clearCheck();
                        }
                        switch (bundle) {
                            case 0: {
                                FW_ID = 1;
                                major = 4;
                                minor = 5;
                                fix = 6;
                                break;
                            }
                            case 1: {
                                FW_ID = 1;
                                major = 10;
                                minor = 11;
                                fix = 12;
                                break;
                            }
                            case 2: {
                                FW_ID = 1;
                                major = 16;
                                minor = 17;
                                fix = 18;
                                break;
                            }
                            case -1: {
                                break;
                            }
                        }
                        break;
                    case R.id.radioButtonCONF0:
                        if(radioButtonId == b.getId()) {
                            radioGroupBundle.clearCheck();
                        }
                        FW_ID = 7;
                        major = 1;
                        minor = 1;
                        fix = 1;
                        break;
                    case R.id.radioButtonCONF1:
                        if(radioButtonId == b.getId()) {
                            radioGroupBundle.clearCheck();
                        }
                        FW_ID = 8;
                        major = 1;
                        minor = 1;
                        fix = 1;
                    break;
                }
            }
        });
        //SEARCH
        sv.setQueryHint("Search..");
        sv.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String txt) {
                // TODO Auto-generated method stub
                return false;
            }

            @Override
            public boolean onQueryTextChange(String txt) {
                // TODO Auto-generated method stub

                adapter.getFilter().filter(txt);
                return false;
            }
        });

        //BUTTON
        cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                dismiss();
            }
        });
        load.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                byte[] data = new byte [] {0x00, 0x01, 0x02};
                if (imageFileName != null) {
                    try {
                        CRC crc = new CRC();
                        String fileName = imageFileName;
                        crc.writeFileHeader(FW_ID,major,minor,fix, CRC.getCrcTable(), fileName);
                        radioGroupImage.clearCheck();
                        radioGroupBundle.clearCheck();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        return rootView;
    }
    public void getAllBinFiles() {
        String path = Environment.getExternalStorageDirectory().toString() + "/Download";
        Log.e("Files", "Path: " + path);
        File directory = new File(path);
        File[] files = directory.listFiles();
        Log.e("Files", "Size: "+ files.length);
        allFiles = new ArrayList<String>();
        for (int i = 0; i < files.length; i++) {
            if(files[i].getName().endsWith(".bin")) {
                allFiles.add(files[i].getName());
                Log.e("Files", "FileName:" + files[i].getName());
            }
        }
        binFiles = new String [allFiles.size()];
        for(int i = 0; i < allFiles.size(); i++) binFiles[i] = allFiles.get(i);
    }
/*    public void writeFileHeader(byte[] data, String fileName) throws IOException {
        File file = new File (fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        out.write(data);
        out.close();
    }  */

}