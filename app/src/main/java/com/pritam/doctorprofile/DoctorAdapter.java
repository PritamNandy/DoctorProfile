package com.pritam.doctorprofile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder> implements Filterable {

    private Context ctx;
    private List<Doctor> doctorList;
    private List<Doctor> doctorListFull;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public DoctorAdapter(Context ctx, List<Doctor> doctorList) {
        this.ctx = ctx;
        this.doctorList = doctorList;
        this.doctorListFull = new ArrayList<>(doctorList);
    }

    @NonNull
    @Override
    public DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.list_layout, null);
        DoctorViewHolder holder = new DoctorViewHolder(view, mListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorViewHolder holder, int position) {
        Doctor doctor = doctorList.get(position);
        holder.textViewName.setText(doctor.getName());
        holder.textViewHospital.setText(doctor.getHospital());
        holder.textViewRating.setText(String.valueOf(doctor.getRating()));
        holder.textViewVisit.setText(String.valueOf(doctor.getVisit()));
        //holder.imageView.setImageDrawable(ctx.getResources().getDrawable(doctor.getImage()));
        Glide.with(ctx)
                .load(doctor.getImage())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return doctorList.size();
    }

    @Override
    public Filter getFilter() {
        return doctorFilter;
    }

    private Filter doctorFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Doctor> filterList = new ArrayList<>();

            if(constraint == null || constraint.length() == 0) {
                filterList.addAll(doctorListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Doctor item: doctorListFull) {
                    if(item.getName().toLowerCase().contains(filterPattern)) {
                        filterList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            doctorList.clear();
            doctorList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    class DoctorViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewName, textViewHospital, textViewRating, textViewVisit;

        public DoctorViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewHospital = itemView.findViewById(R.id.textViewHospital);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewVisit = itemView.findViewById(R.id.textViewVisit);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null) {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

}
