package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    //
    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

    @Override
    protected void insertElement(Resume resume, int index) {
        int insertIndex = (index * -1) - 1;
        Resume[] copyStorage = new Resume[STORAGE_LIMIT];
        System.arraycopy(storage, 0, copyStorage, 0, insertIndex);
        System.arraycopy(storage, insertIndex, copyStorage, insertIndex, size - insertIndex);
        copyStorage[insertIndex] = resume;
        storage = copyStorage;
    }

    @Override
    protected void insertDeletedElement(int index) {
        int remainsCount = size - index - 1;
        if (remainsCount > 0) {
            Resume[] copyStorage = new Resume[STORAGE_LIMIT];
            System.arraycopy(storage, 0, copyStorage, 0, index);
            System.arraycopy(storage, index + 1, copyStorage, index, remainsCount);
            storage = copyStorage;
        }
    }

}
