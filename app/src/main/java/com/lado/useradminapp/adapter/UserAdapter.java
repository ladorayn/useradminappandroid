package com.lado.useradminapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lado.useradminapp.OnClickUserInterface;
import com.lado.useradminapp.R;
import com.lado.useradminapp.databinding.UserDataLayoutBinding;
import com.lado.useradminapp.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    List<User> usersList;
    private OnClickUserInterface onClickUserInterface;

    public UserAdapter(OnClickUserInterface onClickUserInterface) {
        this.onClickUserInterface = onClickUserInterface;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        UserDataLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.user_data_layout, parent, false );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {

        if (usersList!=null) {
            User user = usersList.get(position);
            holder.binding.setUser(user);

            holder.binding.setListener(onClickUserInterface);

        }
    }

    @Override
    public int getItemCount() {

        if (usersList!= null)
            return usersList.size();
        else
            return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        UserDataLayoutBinding binding;
        public ViewHolder(@NonNull UserDataLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public void setUsers(List<User> users) {
        usersList = users;
        notifyDataSetChanged();
    }
}
