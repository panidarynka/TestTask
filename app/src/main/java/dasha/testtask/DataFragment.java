package dasha.testtask;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dasha on 14.09.16.
 */
public class DataFragment extends Fragment {
    EditText editTextColumn, editTextRow, editTextX, editTextY;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, null);
        editTextColumn = (EditText) view.findViewById(R.id.column_edit_text);
        editTextRow = (EditText) view.findViewById(R.id.row_edit_text);
        editTextX = (EditText) view.findViewById(R.id.x_edit_text);
        editTextY = (EditText) view.findViewById(R.id.y_edit_text);

        Button build = (Button) view.findViewById(R.id.build);
        build.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextColumn.getText())) {
                    editTextColumn.setError("Insert columns number");
                } else if (TextUtils.isEmpty(editTextRow.getText())){
                    editTextRow.setError("Insert rows number");
                } else if (TextUtils.isEmpty(editTextX.getText())){
                    editTextX.setError("Insert x position");
                } else if (TextUtils.isEmpty(editTextY.getText())){
                    editTextY.setError("Insert y position");
                }else {

                    int columns = Integer.parseInt(editTextColumn.getText().toString());
                    int rows = Integer.parseInt(editTextRow.getText().toString());
                    int x = Integer.parseInt(editTextX.getText().toString());
                    int y = Integer.parseInt(editTextY.getText().toString());
                    if (x<=Math.min(columns, rows)&& y<=Math.max(columns, rows)) {
                        MatrixFragment matrixFragment = new MatrixFragment();
                        Bundle bundle = new Bundle();
                        bundle.putInt("Columns", columns);
                        bundle.putInt("Rows", rows);
                        bundle.putInt("x_position", x);
                        bundle.putInt("y_position", y);
                        matrixFragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.matrix_fragment_container, matrixFragment).commit();
                    } else
                        Toast.makeText(getActivity(), "X, Y positions must be in matrix range", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}
