package busit.treeFG1.slambud;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList answer_id,answer_text1,answer_text2;

    //I WILL JUST SHOW 1 input which is name and nickname
    CustomAdapter(Context context,
                  ArrayList answer_id,
                  ArrayList answer_text1,
                  ArrayList answer_text2) {
        this.context = context;
        this.answer_id = answer_id;
        this.answer_text1 = answer_text1;
        this.answer_text2 = answer_text2;
    }
    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent,false);
        return new MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, final int position) {

        holder.answer_id_txt.setText(String.valueOf(answer_id.get(position)));
        holder.answer_text1_txt.setText(String.valueOf(answer_text1.get(position)));
        holder.answer_text2_txt.setText(String.valueOf(answer_text2.get(position)));
    }

    @Override
    public int getItemCount() {
        return answer_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView answer_id_txt, answer_text1_txt, answer_text2_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            answer_id_txt = itemView.findViewById(R.id.answer_id_txt);
            answer_text1_txt = itemView.findViewById(R.id.answer_text1_txt);
            answer_text2_txt = itemView.findViewById(R.id.answer_text2_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            // Set OnClickListener for mainLayout
            mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentPosition = getAdapterPosition();
                    if (currentPosition != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent(context, updateActivity.class);
                        intent.putExtra("id", Integer.parseInt(String.valueOf(answer_id.get(currentPosition))));
                        intent.putExtra("Name", String.valueOf(answer_text1.get(currentPosition)));
                        intent.putExtra("Nickname", String.valueOf(answer_text2.get(currentPosition)));
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

}