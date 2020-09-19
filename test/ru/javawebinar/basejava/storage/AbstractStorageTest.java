package ru.javawebinar.basejava.storage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.List;

public abstract class AbstractStorageTest {
    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String UUID_4 = "uuid4";
    private static final Resume RESUME_1;
    private static final Resume RESUME_2;
    private static final Resume RESUME_3;
    private static final Resume RESUME_4;
    private static Resume[] expectedResumes;
    Storage storage;

    static {
        RESUME_1 = new Resume(UUID_1, "John");
        RESUME_2 = new Resume(UUID_2, "Mary");
        RESUME_3 = new Resume(UUID_3, "Ann");
        RESUME_4 = new Resume(UUID_4, "Goblin");
        expectedResumes = new Resume[3];
        expectedResumes[0] = RESUME_1;
        expectedResumes[1] = RESUME_2;
        expectedResumes[2] = RESUME_3;
    }

    public AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }

    @Test
    public void update() throws Exception {
        Resume resume = new Resume(UUID_1, "Ann");
        storage.update(resume);
        Assert.assertEquals(resume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(RESUME_4);
    }

    @Test
    public void getAllSorted() throws Exception {
        List<Resume> actualResumeList = storage.getAllSorted();
        Resume[] actualResumes = new Resume[actualResumeList.size()];
        actualResumes = actualResumeList.toArray(actualResumes);
        Assert.assertEquals(3, actualResumes.length);
        Assert.assertArrayEquals(actualResumes, expectedResumes);
    }

    @Test
    public void save() throws Exception {
        storage.save(new Resume(UUID_4, "Goblin"));
        Assert.assertEquals(4, storage.size());
        Assert.assertEquals(RESUME_4, storage.get(UUID_4));
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_1);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_1);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(UUID_4);
    }

    @Test
    public void get() throws Exception {
        Resume resume = storage.get(UUID_2);
        Assert.assertEquals(RESUME_2, resume);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(RESUME_4.getUuid());
    }


}