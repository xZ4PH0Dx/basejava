package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Test;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.UUID;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    public AbstractArrayStorageTest(AbstractStorage storage) {
        super(storage);
    }

    @Test(expected = StorageException.class)
    public void getStorageOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("John" + i));
            }
        } catch (StorageException e) {
            Assert.fail("StorageException caught in a wrong catch!");
        }
        storage.save(new Resume("John", UUID.randomUUID().toString()));
    }
}