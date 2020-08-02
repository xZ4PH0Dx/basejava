package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    @Override
    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.println("Couldn't update Resume. No such Resume");
        }
    }

    @Override
    public void save(Resume resume) {
        if (size < AbstractArrayStorage.STORAGE_LIMIT) {
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                insertElement(resume, index);
                size++;
            } else {
                System.out.println("There's a Resume with uuid:" + resume.getUuid() + " in storage");
            }
        } else {
            System.out.println("Resume storage has max items");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            insertDeletedElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Couldn't delete Resume. No such Resume");
        }
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void insertDeletedElement(int index);
}