package ru.javawebinar.basejava;

import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.AbstractArrayStorage;
import ru.javawebinar.basejava.storage.ArrayStorage;
import ru.javawebinar.basejava.storage.SortedArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        System.out.println("Testing SortedArrayStorage\n");
        testStorage(SORTED_ARRAY_STORAGE);
        System.out.println();
        System.out.println("Testing ArrayStorage\n");
        testStorage(ARRAY_STORAGE);
    }

    static void printAll(AbstractArrayStorage storage) {
        System.out.println("\nGet All");
        for (Resume r : storage.getAll()) {
            System.out.println(r);
        }
    }

    static void testStorage(AbstractArrayStorage storage) {
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");

        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
        storage.save(r4);

        System.out.println("Get r1: " + storage.get(r1.getUuid()));
        System.out.println("Size: " + storage.size());

        System.out.println("Get dummy: " + storage.get("dummy"));

        printAll(storage);
        storage.delete(r1.getUuid());
        printAll(storage);
        storage.clear();
        printAll(storage);

        System.out.println("Size: " + storage.size());
    }
}
