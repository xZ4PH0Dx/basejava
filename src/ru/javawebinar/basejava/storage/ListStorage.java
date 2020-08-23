package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;

public class ListStorage extends AbstractStorage {
    protected ArrayList<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public Resume[] getAll() {
        return storage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void doUpdate(Resume resume, int index) {
        storage.set(index, resume);
    }

    @Override
    public void doSave(Resume resume, int index, String uuid) {
        storage.add(resume);
    }

    @Override
    public void doDelete(int index) {
        storage.remove(index);
    }

    @Override
    public Resume doGet(int index) {
        return storage.get(index);
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < storage.size(); i++) {
            if (storage.get(i).getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
