package com.kieronwiltshire.essential_services.core.api;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.index.Index;

import java.util.Collection;

public interface IndexService {

    /**
     * Add an index
     *
     * @param type The index object
     * @param index The index instance
     * @param <T> The type of index
     */
    <T extends Index> void add(Class<T> type, T index);

    /**
     * Remove an index
     *
     * @param type The index object
     * @param <T> The type of index
     */
    <T extends Index> void remove(Class<T> type);

    /**
     * Get all indexes
     *
     * @return All of indexes
     */
    Collection<Index> getIndicies();

    /**
     * Get a specific index
     *
     * @param type The index object
     * @param <T> The type of index
     * @return The index instance
     */
    <T extends Index> Optional<T> getIndex(Class<T> type);

}
