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
    void clear() {
        for (Resume r : storage
        ) {
            r = null;
        }
    }

    /**
     * Saves resume to storage
     */
    void save(Resume r) {
        storage[lastItem] = r;
        lastItem++;
    }

    /**
     * @return Resume by uuid. Null if not exists such uuid
     */
    Resume get(String uuid) {
        for (int i = 0; i < lastItem; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    /**
     * Delete uuid from storage
     */
    void delete(String uuid) {
        System.out.println(lastItem);
        for (int i = 0; i < lastItem; i++) {
            if (storage[i].uuid.equals(uuid)) {
                for (int j = i; j < lastItem - 1; j++) {
                    storage[j] = storage[j + 1];
                    storage[j + 1] = null;
                }
                lastItem--;
                break;
            }
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, lastItem);
    }

    /**
     * @return count of Resume in storage
     */
    int size() {
        return lastItem;
    }
}
