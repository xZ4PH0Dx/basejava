package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage<Resume> {
    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected boolean isExist(Resume key) {
        return key != null;
    }

    @Override
    protected List<Resume> getResumeList() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    protected void doUpdate(Resume resume, Resume key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, Resume key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(Resume key) {
        storage.remove(key.getUuid());
    }

    @Override
    protected Resume doGet(Resume key) {
        return storage.get(key.getUuid());
    }

    @Override
    protected Resume getKey(String uuid) {
        Resume resume = storage.get(uuid);
        return (resume != null) ? resume : null;
    }
}
