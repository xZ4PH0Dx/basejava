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
        String uuid = resume.getUuid();
        int index = getIndex(uuid);
        if (index != -1) {
            storage[index] = resume;
        } else {
            System.out.printf("Couldn't update Resume with uuid: %s No such Resume\n", uuid);
        }
    }

    @Override
    public void save(Resume resume) {
        String uuid = resume.getUuid();
        if (size < STORAGE_LIMIT) {
            int index = getIndex(resume.getUuid());
            if (index < 0) {
                insertElement(resume, index);
                size++;
            } else {
                System.out.printf("There's exist a Resume with uuid: %s in storage\n", uuid);
            }
        } else {
            System.out.println("Resume storage has max items");
        }
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            fillDeletedElement(index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.printf("Couldn't delete Resume with uuid: %s. No such Resume\n", uuid);
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
            System.out.printf("Resume uuid: %s not exist\n", uuid);
            return null;
        }
        return storage[index];
    }

    protected abstract int getIndex(String uuid);

    protected abstract void insertElement(Resume resume, int index);

    protected abstract void fillDeletedElement(int index);
}