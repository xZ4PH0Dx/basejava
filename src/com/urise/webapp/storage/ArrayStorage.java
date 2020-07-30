package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;


/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    int lastItem = 0;

    /**
     * Clear storage
     */
    public void clear() {
        Arrays.fill(storage, null);
        lastItem = 0;
    }

    /**
     * Saves resume to storage
     */
    public void save(Resume r) {
        storage[lastItem] = r;
        lastItem++;
    }

    /**
     * @return com.urise.webapp.model.Resume by uuid. Null if not exists such uuid
     */
    public Resume get(String uuid) {
        for (int i = 0; i < lastItem; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    /**
     * Delete uuid from storage
     */
    public void delete(String uuid) {
        for (int i = 0; i < lastItem; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                storage[i] = storage[lastItem - 1];
                storage[size() - 1] = null;
                lastItem--;
                break;
            }
        }
    }

    public void update(Resume resume){

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
}
