package com.example.diary_project;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewCustomAdpter extends RecyclerView.Adapter<ViewCustomAdpter.ViewHolder> {

    private ArrayList<TodoItem> mTodoItems;
    private Context mContext;
    private DBHepler mDBHelper;
    int iconNum;


    public ViewCustomAdpter(ArrayList<TodoItem> mTodoItems, Context mContext) {
        this.mTodoItems = mTodoItems;
        this.mContext = mContext;
        mDBHelper = new DBHepler(mContext);
    }

    @NonNull
    @Override
    public ViewCustomAdpter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //뷰연결
        View holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_list,parent,false);
        return new ViewHolder(holder);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCustomAdpter.ViewHolder holder, int position) {
        holder.tv_title.setText(mTodoItems.get(position).getTitle());
        holder.tv_content.setText(mTodoItems.get(position).getContent());
        holder.tv_writeDate.setText(mTodoItems.get(position).getWriteDate());

        // 아이콘 사진 번경
        int icon = mTodoItems.get(position).getIcon();
        if(icon == 0)
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_pleasure);
        else if(icon == 1)
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_angry);
        else if(icon == 2)
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_sad);
        else if(icon == 3)
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_joy);
        else if(icon == 4)
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_love);
        else if(icon == 5)
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_hatred);
        else if(icon == 6)
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_craving);
        else
            holder.iv_icon.setImageResource(R.mipmap.ic_launcher_pleasure);

    }

    @Override
    public int getItemCount() {
        return mTodoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv_title;
        private TextView tv_content;
        private TextView tv_writeDate;
        private ImageView iv_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_title = itemView.findViewById(R.id.tv_title);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_writeDate = itemView.findViewById(R.id.tv_date);
            iv_icon = itemView.findViewById(R.id.iv_icon);

            //하나의 아이템뷰를 터치시 수정가능
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int curPos = getAdapterPosition();      //현재 선택한 리스트 아이템 위치
                    TodoItem todoItem = mTodoItems.get(curPos);  // 선택한 아이템 값들

                    String[] strChoiceItems= {"수정하기","삭제하기"};
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("원하는 작업을 선택해 주세요");
                    builder.setItems(strChoiceItems, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int position) {
                            if (position == 0){
                                //수정하기
                                //팝업창 띄우기
                                Dialog dialog = new Dialog(mContext, android.R.style.Theme_Material_Light_Dialog);
                                dialog.setContentView(R.layout.dialog_edit);
                                EditText et_title = dialog.findViewById(R.id.et_title);
                                EditText et_content = dialog.findViewById(R.id.et_content);
                                Button btn_ok = dialog.findViewById(R.id.btn_ok);

                                et_title.setText(todoItem.getTitle());
                                et_content.setText(todoItem.getContent());
                                // 커서를 글자의 마지막으로 이동
                                et_title.setSelection(et_title.getText().length());
                                ImageButton imgbtn_pleasure =dialog.findViewById(R.id.imgbtn_pleasure);
                                ImageButton imgbtn_anger = dialog.findViewById(R.id.imgbtn_anger);
                                ImageButton imgbtn_sad=dialog.findViewById(R.id.imgbtn_sad);
                                ImageButton imgbtn_joy= dialog.findViewById(R.id.imgbtn_joy);
                                ImageButton imgbtn_love=dialog.findViewById(R.id.imgbtn_love);
                                ImageButton imgbtn_hatred=dialog.findViewById(R.id.imgbtn_hatred);
                                ImageButton imgbtn_craving=dialog.findViewById(R.id.imgbtn_craving);


                                //기쁨이벤트
                                imgbtn_pleasure.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        iconNum = 0;
                                        Log.d("dd", String.valueOf(iconNum));
                                    }
                                });

                                //화남
                                imgbtn_anger.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        iconNum = 1;
                                        Log.d("dd", String.valueOf(iconNum));
                                    }
                                });

                                //슬픔
                                imgbtn_sad.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        iconNum = 2;
                                        Log.d("dd", String.valueOf(iconNum));
                                    }
                                });
                                //즐거움
                                imgbtn_joy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        iconNum = 3;
                                        Log.d("dd", String.valueOf(iconNum));
                                    }
                                });
                                //사랑
                                imgbtn_love.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        iconNum = 4;
                                        Log.d("dd", String.valueOf(iconNum));
                                    }
                                });
                                //증오
                                imgbtn_hatred.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        iconNum = 5;
                                        Log.d("dd", String.valueOf(iconNum));
                                    }
                                });
                                //욕망
                                imgbtn_craving.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        iconNum = 6;
                                        Log.d("dd", String.valueOf(iconNum));
                                    }
                                });

                                btn_ok.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        // update database
                                        String title = et_title.getText().toString();
                                        String content = et_content.getText().toString();
                                        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//현재 시간 월일시 받아오기
                                        String beforeTime = todoItem.getWriteDate();  //이전에 작성한 시간


                                        mDBHelper.UpdateTodo(title, content, iconNum,currentTime,beforeTime);

                                        // update UI
                                        todoItem.setTitle(title);
                                        todoItem.setContent(content);
                                        todoItem.setWriteDate(currentTime);
                                        todoItem.setIcon(iconNum);
                                        notifyItemChanged(curPos, todoItem);
                                        dialog.dismiss();
                                        Toast.makeText(mContext, "목록 수정이 완료 되었습니다", Toast.LENGTH_SHORT).show();

                                    }
                                });

                                dialog.show();
                            }
                            else if(position == 1){
                                //delete table
                                String beforeTime = todoItem.getWriteDate();  //이전에 작성한 시간
                                mDBHelper.deleteTodo(beforeTime);

                                //delete UI
                                mTodoItems.remove(curPos);
                                notifyItemRemoved(curPos);
                                Toast.makeText(mContext, "목록이 제거 되었습니다", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                    builder.show();
                }
            });
        }
    }
    // 액티비티에서 호출되는 함수이며, 현재 어댑터에 새로운 게시글 아이템을 전달받아 추가하는 목적
    public void addItem(TodoItem _item){
        mTodoItems.add(0, _item);  //최신 데이터가 위로 올라오게
        notifyItemInserted(0);    //새로고침
    }


}
