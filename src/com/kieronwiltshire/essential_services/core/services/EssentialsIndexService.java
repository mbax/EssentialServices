package com.kieronwiltshire.essential_services.core.services;

import com.google.common.base.Optional;
import com.kieronwiltshire.essential_services.core.api.IndexService;
import com.kieronwiltshire.essential_services.core.api.index.Index;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EssentialsIndexService implements IndexService {

    private Set<Index> indices;

    /**
     * EssentialsIndexService constructor
     */
    public EssentialsIndexService() {
        this.indices = new HashSet<Index>();
    }

    @Override
    public <T extends Index> void add(Class<T> type, T index) {
        for (Index i : this.indices) {
            if (i.getClass().isInstance(type)) return;
        }
        indices.add(index);
    }

    @Override
    public <T extends Index> void remove(Class<T> type) {
        for (Iterator<Index> i = this.indices.iterator(); i.hasNext();) {
            if (i.getClass().isInstance(type)) i.remove();
        }
    }

    @Override
    public Collection<Index> getIndicies() {
        return this.indices;
    }

    @Override
    public <T extends Index> Optional<T> getIndex(Class<T> type) {
        for (Iterator<Index> i = this.indices.iterator(); i.hasNext();) {
            if (i.getClass().isInstance(type)) return Optional.of(type.cast(i));
        }
        return Optional.<T>absent();
    }

}
