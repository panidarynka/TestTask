package dasha.testtask;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dasha on 14.09.16.
 */
public class MatrixFragment extends Fragment {
    int columns, rows, xPosition, yPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        columns = bundle.getInt("Columns", 0);
        rows = bundle.getInt("Rows", 0);
        xPosition = bundle.getInt("x_position", 0);
        yPosition = bundle.getInt("y_position", 0);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_matrix, null);
        ArrayAdapter adapter = new ArrayAdapter<>(getActivity(), R.layout.item, R.id.contentMatrix,
                createList(columns, rows, xPosition - 1, yPosition - 1));
        GridView matrixGridView = (GridView) view.findViewById(R.id.matrix_grid_view);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.grid_item_anim);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
        matrixGridView.setNumColumns(columns);
        matrixGridView.setAdapter(adapter);
        matrixGridView.setLayoutAnimation(controller);
        return view;
    }

    private List createList(int columns, int rows, int xPosition, int yPosition) {
        List list = new ArrayList();
        int[][] matrix = new int[rows][columns];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
        for (int k = Math.max(columns, rows); k > 0; k--) {
            for (int row = xPosition - k; row <= xPosition + k; row++) {
                for (int col = yPosition - k; col <= yPosition + k; col++) {
                    if (!(xPosition == row && yPosition == col)) {
                        try {
                            matrix[row][col] = k;
                        } catch (Exception e) {
                            Log.e("Exception", e.toString());
                        }

                    }

                }
            }
        }

        for (int i = 0; i < matrix.length; i++, System.out.println()) {
            for (int j = 0; j < matrix[i].length; j++) {
//                Log.d("Matrix", matrix[i][j] + " " + i + " " + j);
                list.add(matrix[i][j]);
            }
        }
        return list;
    }
}
