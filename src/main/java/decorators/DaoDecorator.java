package decorators;

import interfaces.ICrudDao;

public abstract class DaoDecorator {
    protected ICrudDao baseDao;
    protected ICrudDao relatedDao;

    public DaoDecorator(ICrudDao baseDao, ICrudDao relatedDao) {
        this.baseDao = baseDao;
        this.relatedDao = relatedDao;
    }


}
