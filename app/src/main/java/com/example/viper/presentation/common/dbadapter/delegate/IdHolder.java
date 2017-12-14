package com.example.viper.presentation.common.dbadapter.delegate;

/**
 * Interface to help BindableAdapter getting itemId for items
 * You must implementing this interface in you custom AdapterDelegates
 * if you want Adapter to be aware of itemIds.
 *
 * @param <T> the type of data source
 */
public interface IdHolder<T> {

    /**
     * Get stable id for item in data source by position
     *
     * @param items    data source
     * @param position item position in the data source
     * @return stable item id
     */
    long getItemId(T items, int position);
}