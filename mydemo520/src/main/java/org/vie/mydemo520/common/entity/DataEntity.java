package org.vie.mydemo520.common.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.vie.mydemo520.entity.sys.User;


/**
 * 实体类通用数据基类
 * 
 * @author Edward
 * 
 */
public class DataEntity<T>  {

	private int id; // 主键

	private Integer isDelete; // 默认0 删除1(用于逻辑删除)

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime; // 创建时间

	private User createUser; // 创建人

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime; // 最后修改时间

	private User updateUser; // 最后修改人

	private String remark; // 备注
	
	private String createStartTime;
	private String createEndTime;

	private String updateStartTime;
	private String updateEndTime;


	public DataEntity() {
	}
	
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreateUser() {
		return createUser;
	}

	public void setCreateUser(User createUser) {
		this.createUser = createUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public User getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(User updateUser) {
		this.updateUser = updateUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

	public String getUpdateStartTime() {
		return updateStartTime;
	}

	public void setUpdateStartTime(String updateStartTime) {
		this.updateStartTime = updateStartTime;
	}

	public String getUpdateEndTime() {
		return updateEndTime;
	}

	public void setUpdateEndTime(String updateEndTime) {
		this.updateEndTime = updateEndTime;
	}

}
