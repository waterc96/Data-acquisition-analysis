package com.briup.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �����¶ȡ�ʪ�ȡ�co2������ǿ������������
 * @author faple
 * @created 2018��9��20�� ����2:25:57
 */
public class Environment implements Serializable{
	/**
	 * ���л��汾ID
	 */
	private static final long serialVersionUID = -7517354848519437112L;
	/** �������� */
	private String name;
	/** ���Ͷ�ID */
	private Integer srcId;
	/** ��ݮ��ID */
	private Integer desId;
	/** �豸ID */
	private Integer devId;
	/** ������ID */
	private Integer sensorAddress;
	/** ���������� */
	private Integer counter;
	/** ָ�� 3��ʾ���գ�16��ʾ���� */
	private Integer cmd;
	/** ������ֵ */
	private Double data;
	/** ״̬ 1��ʾ�ɹ� 0��ʾʧ�� */
	private Integer status;
	/** ���ݲɼ�ʱ�� */
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
