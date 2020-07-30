package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_STORAGE_SIZE = 10000;
    private final Resume[] storage = new Resume[MAX_STORAGE_SIZE];
    private int lastItem = 0;


    /**
     * Clear storage
     */
    public void clear() {
        Arrays.fill(storage, 0, lastItem, null);
        lastItem = 0;
    }

    /**
     * Saves resume to storage
     */
    public void save(Resume r) {
        if (lastItem < MAX_STORAGE_SIZE) {
            int index = getResumeIndex(r.getUuid());
            if (index == -1) {
                storage[lastItem] = r;
                lastItem++;
            } else {
                System.out.println("There's a Resume with uuid:" + r.getUuid() + " in storage");
            }
        } else {
            System.out.println("Resume storage has max items");
        }
    }

    /**
     * @return com.urise.webapp.model.Resume by uuid. Null if not exists such uuid
     */
    public Resume get(String uuid) {
        int index = getResumeIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            printError("get");
            return null;
        }
    }

    /**
     * Delete uuid from storage
     */
    public void delete(String uuid) {
        int index = getResumeIndex(uuid);
        if (index != -1) {
            storage[index] = storage[lastItem - 1];
            storage[size() - 1] = null;
            lastItem--;
            return;
        } else {
            printError("delete");
        }
    }

    /**
     * Update Resume
     */
    public void update(Resume resume) {
        int index = getResumeIndex(resume.getUuid());
        if (index != -1) {
            storage[index] = resume;
            return;
        } else {
            printError("update");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, lastItem);
    }

    /**
     * @return count of com.urise.webapp.model.Resume in storage
     */
    public int size() {
        return lastItem;
    }

    public void printError(String string) {
        System.out.println("Couldn't " + string + " Resume. No such Resume");
    }

    private int getResumeIndex(String uuid) {
        for (int i = 0; i < lastItem; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
