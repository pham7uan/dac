package vn.seven.stc.core.filterstorage;

import vn.seven.stc.core.CustomJpaRepository;

import java.util.List;

public interface FilterStorageRepository extends CustomJpaRepository<FilterStorage,Long> {
    int countAllByNameAndModel(String name, String model);
    FilterStorage findFirstByNameAndModel(String name, String model);

    List<FilterStorage> findAllByModel(String model);
}
