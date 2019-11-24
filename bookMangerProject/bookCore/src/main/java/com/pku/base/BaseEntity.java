package com.pku.base;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

@MappedSuperclass
public class BaseEntity extends Entity{

	private static final long serialVersionUID = 7054678755086215711L;

	@Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @Version
    @Column(name = "VERSION")
    private Integer version = 0;

    /***
     * 创建人ID
     */
    @Column(name = "CREATOR_ID")
    private String creatorId = "admin";

    /**
     * 创建人姓名
     */
    @Column(name = "CREATOR_NAME")
    private String creatorName = "系统";

    /**
     * 创建人所属部门id
     */
    @Column(name = "CREATOR_DEPARTMENT_ID")
    private Long creatorDepartmentId = 100L;

    /**
     * 创建人所属部门名称
     */
    @Column(name = "CREATOR_DEPARTMENT_NAME")
    private String creatorDepartmentName = "";

    /***
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /***
     * 修改人域账号
     */
    @Column(name = "UPDATOR_ID")
    private String updatorId;

    /**
     * 修改人姓名
     */
    @Column(name = "UPDATOR_NAME")
    private String updatorName;

    /**
     * 修改人所属部门id
     */
    @Column(name = "UPDATOR_DEPARTMENT_ID")
    private Long updatorDepartmentId;

    /**
     * 修改人所属部门名称
     */
    @Column(name = "UPDATOR_DEPARTMENT_NAME")
    private String updatorDepartmentName;

    /***
     * 修改时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /***
     * 是否删除状态，true为已删除，false为未删除
     */
    @Column(name = "IS_DELETED")
    private Boolean deleted = false;
    
    
    @Transactional
    public BaseEntity save() {
    	EntityManager em = getEntityManagerFactory().createEntityManager();
        if (existed(this) == false || this.notExisted()) {
        	em.getTransaction().begin();
        	em.persist(this);
        	em.getTransaction().commit();
        	em.clear();
            logger.info("create a entity: " + this.getClass() + "/" + this.getId() + ".");
            return this;
        } else {
        	em.getTransaction().begin();
        	BaseEntity obj = em.merge(this);
        	em.getTransaction().commit();
        	em.clear();
            logger.info("update a entity: " + this.getClass() + "/" + this.getId() + ".");
            return obj;
        }
    }
    
    public boolean existed(BaseEntity entity) {
        Object id = entity.getId();
        if (id == null) {
            return false;
        }
        if (id instanceof Number && ((Number) id).intValue() == 0) {
            return false;
        }
        return true;
    }
    
    
    public boolean notExisted() {
        return !existed();
    }
    
    public boolean existed() {
        Object id = getId();
        if (id == null) {
            return false;
        }
        if (((id instanceof Number)) && (((Number) id).intValue() == 0)) {
            return false;
        }
        return exists(getClass(), getId());
    }
    
    public <T extends BaseEntity> boolean exists(final Class<T> clazz, final Serializable id) {
        T entity = getEntityManager().find(clazz, id); 
        return entity != null;
    }
    
    /**
     * 将实体本身从数据库中删除
     */
    public void remove() {
    	getEntityManager().remove(this);
    }
    
    /**
     * 修改方法
     * 
     * @return
     */
    public String update() {
        this.setUpdateTime(new Date());
        this.save();
        return this.id;
    }

    /**
     * 逻辑删除方法
     */
    public void logicRemove() {
        this.setDeleted(true);
        this.update();
    }
    
    public static <T extends BaseEntity> T get(final Class<T> clazz, final Serializable id) {
    	return Entity.getEntityManager().find(clazz, id);
    }
    
    public <T extends BaseEntity> T getEntity(final Class<T> clazz, final Serializable id) {
    	return getEntityManagerFactory().createEntityManager().find(clazz, id);
    }
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Long getCreatorDepartmentId() {
		return creatorDepartmentId;
	}

	public void setCreatorDepartmentId(Long creatorDepartmentId) {
		this.creatorDepartmentId = creatorDepartmentId;
	}

	public String getCreatorDepartmentName() {
		return creatorDepartmentName;
	}

	public void setCreatorDepartmentName(String creatorDepartmentName) {
		this.creatorDepartmentName = creatorDepartmentName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(String updatorId) {
		this.updatorId = updatorId;
	}

	public String getUpdatorName() {
		return updatorName;
	}

	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	public Long getUpdatorDepartmentId() {
		return updatorDepartmentId;
	}

	public void setUpdatorDepartmentId(Long updatorDepartmentId) {
		this.updatorDepartmentId = updatorDepartmentId;
	}

	public String getUpdatorDepartmentName() {
		return updatorDepartmentName;
	}

	public void setUpdatorDepartmentName(String updatorDepartmentName) {
		this.updatorDepartmentName = updatorDepartmentName;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
}

