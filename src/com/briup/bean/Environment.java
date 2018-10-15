package com.briup.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 保存温度、湿度、co2、光照强度这四类数据
 * @author faple
 * @created 2018年9月20日 下午2:25:57
 */
public class Environment implements Serializable{
	/**
	 * 串行化版本ID
	 */
	private static final long serialVersionUID = -7517354848519437112L;
	/** 数据名称 */
	private String name;
	/** 发送端ID */
	private Integer srcId;
	/** 树莓派ID */
	private Integer desId;
	/** 设备ID */
	private Integer devId;
	/** 传感器ID */
	private Integer sensorAddress;
	/** 传感器个数 */
	private Integer counter;
	/** 指令 3表示接收，16表示发送 */
	private Integer cmd;
	/** 环境数值 */
	private Double data;
	/** 状态 1表示成功 0表示失败 */
	private Integer status;
	/** 数据采集时间 */
	private Timestamp time;
	
	
	public Environment() {
	}


	public Environment(String name, Integer srcId, Integer desId, Integer devId, Integer sensorAddress, Integer counter,
			Integer cmd, Double data, Integer status, Timestamp time) {
		super();
		this.name = name;
		this.srcId = srcId;
		this.desId = desId;
		this.devId = devId;
		this.sensorAddress = sensorAddress;
		this.counter = counter;
		this.cmd = cmd;
		this.data = data;
		this.status = status;
		this.time = time;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getSrcId() {
		return srcId;
	}


	public void setSrcId(Integer srcId) {
		this.srcId = srcId;
	}


	public Integer getDesId() {
		return desId;
	}


	public void setDesId(Integer desId) {
		this.desId = desId;
	}


	public Integer getDevId() {
		return devId;
	}


	public void setDevId(Integer devId) {
		this.devId = devId;
	}


	public Integer getSensorAddress() {
		return sensorAddress;
	}


	public void setSensorAddress(Integer sensorAddress) {
		this.sensorAddress = sensorAddress;
	}


	public Integer getCounter() {
		return counter;
	}


	public void setCounter(Integer counter) {
		this.counter = counter;
	}


	public Integer getCmd() {
		return cmd;
	}


	public void setCmd(Integer cmd) {
		this.cmd = cmd;
	}


	public Double getData() {
		return data;
	}


	public void setData(Double data) {
		this.data = data;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Timestamp getTime() {
		return time;
	}


	public void setTime(Timestamp time) {
		this.time = time;
	}


	@Override
	public String toString() {
		return "Environment [name=" + name + ", srcId=" + srcId + ", desId=" + desId + ", devId=" + devId
				+ ", sensorAddress=" + sensorAddress + ", counter=" + counter + ", cmd=" + cmd + ", data=" + data
				+ ", status=" + status + ", time=" + time + "]";
	}
	
	
}
