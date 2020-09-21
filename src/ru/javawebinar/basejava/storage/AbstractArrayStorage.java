package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;
import java.util.List;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    public static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    protected boolean isExist(Integer key) {
        return (int) key >= 0;
    }

    @Override
    public List<Resume> getResumeList() {
        return Arrays.asList(storage);
    }

    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(Integer key) {
        return storage[(int) key];
    }

    @Override
    protected void doSave(Resume resume, Integer key) {
        if (size < STORAGE_LIMIT) {
            insertElement(resume, key);
            size++;
        } else {
            throw new StorageException("Storage overflow", null);
        }
    }

    @Override
    public void doUpdate(Resume resume, Integer key) {
        storage[key] = resume;
    }

    @Override
    protected void doDelete(Integer key) {
        fillDeletedElement(key);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public List<Resume> getAllSorted() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    protected abstract Integer getKey(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}