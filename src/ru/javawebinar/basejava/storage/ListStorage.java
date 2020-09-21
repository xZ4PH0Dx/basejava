package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage<Integer> {
    private final List<Resume> storage = new ArrayList<>();

    @Override
    protected boolean isExist(Integer key) {
        return key != null;
    }

    @Override
    protected List<Resume> getResumeList() {
        return storage;
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public List<Resume> getAllSorted() {
        return storage;
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void doUpdate(Resume resume, Integer key) {
        storage.set(key, resume);
    }

    @Override
    public void doSave(Resume resume, Integer key) {
        storage.add(resume);
    }

    @Override
    public void doDelete(Integer key) {
        storage.remove(key.intValue());
    }

    @Override
    public Resume doGet(Integer key) {
        return storage.get(key);
    }

    @Override
    public Integer getKey(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return null;
    }
}
