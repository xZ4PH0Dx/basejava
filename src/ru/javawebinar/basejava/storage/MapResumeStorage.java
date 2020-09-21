package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MapResumeStorage extends AbstractStorage<String> {
    private final Map<String, Resume> storage = new LinkedHashMap<>();

    @Override
    protected boolean isExist(String key) {
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
    protected void doUpdate(Resume resume, String key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doSave(Resume resume, String key) {
        storage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doDelete(String key) {

        storage.remove(key);
    }

    @Override
    protected Resume doGet(String key) {
        return storage.get(key);
    }

    @Override
    protected String getKey(String uuid) {
        return storage.get(uuid) == null ? null : uuid;
    }
}
