package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected abstract void doUpdate(Resume resume, Object key);

    protected abstract void doSave(Resume resume, Object key);

    protected abstract void doDelete(Object key);

    protected abstract Resume doGet(Object key);

    protected abstract Object getKey(String uuid);

    protected abstract boolean isExist(Object key);

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        Object key = getKeyIfExist(uuid);
        doUpdate(resume, key);
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        Object key = getKeyIfNotExist(uuid);
        doSave(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        Object key = getKeyIfExist(uuid);
        return doGet(key);
    }

    @Override
    public void delete(String uuid) {
        Object key = getKeyIfExist(uuid);
        doDelete(key);
    }

    private Object getKeyIfNotExist(String uuid) {
        Object key = getKey(uuid);
        if (!isExist(key)) {
            return key;
        }
        throw new ExistStorageException(uuid);
    }

    private Object getKeyIfExist(String uuid) {
        Object key = getKey(uuid);
        if (isExist(key)) {
            return key;
        }
        throw new NotExistStorageException(uuid);
    }

}
