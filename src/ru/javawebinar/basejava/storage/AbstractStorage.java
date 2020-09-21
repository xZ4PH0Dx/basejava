package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Collections;
import java.util.List;

public abstract class AbstractStorage<SK> implements Storage {
    protected abstract void doUpdate(Resume resume, SK key);

    protected abstract void doSave(Resume resume, SK key);

    protected abstract void doDelete(SK key);

    protected abstract Resume doGet(SK key);

    protected abstract SK getKey(String uuid);

    protected abstract boolean isExist(SK key);

    protected abstract List<Resume> getResumeList();

    @Override
    public void update(Resume resume) {
        String uuid = resume.getUuid();
        SK key = getKeyIfExist(uuid);
        doUpdate(resume, key);
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        SK key = getKeyIfNotExist(uuid);
        doSave(resume, key);
    }

    @Override
    public Resume get(String uuid) {
        SK key = getKeyIfExist(uuid);
        return doGet(key);
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumeList = getResumeList();
        Collections.sort(resumeList);
        return resumeList;
    }

    @Override
    public void delete(String uuid) {
        SK key = getKeyIfExist(uuid);
        doDelete(key);
    }

    private SK getKeyIfNotExist(String uuid) {
        SK key = getKey(uuid);
        if (!isExist(key)) {
            return key;
        }
        throw new ExistStorageException(uuid);
    }

    private SK getKeyIfExist(String uuid) {
        SK key = getKey(uuid);
        if (isExist(key)) {
            return key;
        }
        throw new NotExistStorageException(uuid);
    }

}
