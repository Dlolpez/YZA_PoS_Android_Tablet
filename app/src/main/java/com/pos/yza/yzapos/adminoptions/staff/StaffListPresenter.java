package com.pos.yza.yzapos.adminoptions.staff;

import android.support.annotation.NonNull;

import com.pos.yza.yzapos.SessionStorage;
import com.pos.yza.yzapos.data.representations.Staff;
import com.pos.yza.yzapos.data.source.StaffDataSource;
import com.pos.yza.yzapos.data.source.StaffRepository;
import com.pos.yza.yzapos.util.OnVolleyResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dlolpez on 12/1/18.
 */

public class StaffListPresenter implements StaffListContract.Presenter, OnVolleyResponse {
    private final StaffListContract.View mStaffListView;

    private final StaffRepository mStaffRepository;

    public StaffListPresenter(@NonNull StaffRepository staffRepository,
                              @NonNull StaffListContract.View view){
        mStaffRepository = staffRepository;
        mStaffListView = view;

        mStaffListView.setPresenter(this);
    }

    @Override
    public void start() {
        loadStaff();
    }

    @Override
    public void loadStaff() {
        mStaffRepository.getAllStaff(new StaffDataSource.LoadStaffCallback() {
            @Override
            public void onStaffLoaded(List<Staff> staffList) {
                mStaffListView.showStaff(staffList);
            }

            @Override
            public void onDataNotAvailable() {
            }
        });

    }

    @Override
    public void addStaffMember() {
        mStaffListView.showAddStaffMember();
    }

    @Override
    public void deleteStaffMember(Staff member) {
        String staffId = Integer.toString(member.getStaffId());
        mStaffRepository.deleteStaff(new StaffDataSource.ModifyStaffCallback() {
            @Override
            public void onStaffModified() {
                mStaffListView.showDeleteStaffMember();
                loadStaff();
            }

            @Override
            public void onStaffNotModified() {
                mStaffListView.showErrorDeleteStaffMember();
            }
        }, staffId);

    }

    @Override
    public void editStaffMember(Staff member) {

    }

    // OnResponseVolley methods to handle network response

    @Override
    public void onCompleted() {
        loadStaff();
        mStaffListView.showDeleteStaffMember();
    }

    @Override
    public void onError() {
        mStaffListView.showErrorDeleteStaffMember();
    }
}
