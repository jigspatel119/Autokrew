package com.autokrew.models;

/**
 * 
 * @author anfer
 * 
 */
public class Model {

	private String mLeaveType;
	private String mEligible;
	private String mAvailed;
	private String mBalance;
	private String mIsApplyLeave;

	public Model(String mLeaveType, String mEligible, String mAvailed, String mBalance , String mIsApplyLeave) {
		this.mLeaveType = mLeaveType;
		this.mEligible = mEligible;
		this.mAvailed = mAvailed;
		this.mBalance = mBalance;
		this.mIsApplyLeave = mIsApplyLeave;
	}

	public String getLeaveType() {
		return mLeaveType;
	}

	public String getEligible() {
		return mEligible;
	}

	public String getAvailed() {
		return mAvailed;
	}

	public String getBalance() {
		return mBalance;
	}


	public String getIsApplyLeave() {
		return mIsApplyLeave;
	}




}
