package org.xiongmaotailang.mybatis.batchinsert.model;
/**
 * 
 * @author xiongmaotailang
 *
 */
public class NewsPvUv {


	public int getPv() {
		return pv;
	}


	public void setPv(int pv) {
		this.pv = pv;
	}


	public int getUv() {
		return uv;
	}


	public void setUv(int uv) {
		this.uv = uv;
	}


	int pv;

    String time;
	public NewsPvUv(int pv, int uv, String time) {
		super();
		this.pv = pv;
		this.time = time;
		this.uv = uv;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	int uv;
}
