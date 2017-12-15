package com.vershininds.mixture.sample.presentation.common.dbadapter.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import static android.support.v7.widget.RecyclerView.LayoutManager;
import static android.support.v7.widget.RecyclerView.OnScrollListener;

/**
 * RecyclerView ScrollListener for implementing lazy loading list (endless list)
 * <pre>
 * {@code
 *      ListConfig listConfig = new ListConfig.Builder(mAdapter)
 *          .addOnScrollListener(new OnLoadMoreScrollListener(
 *              new OnLoadMoreListener {
 *                  public void onLoadMore() {
 *                      //Load new items
 *                  }
 *
 *                  public boolean isLoading() {
 *                      return isLoading; // true if loading in progress
 *                  }
 *              }
 *          ))
 *          .build(context);
 * }
 * </pre>
 */
public class LoadMoreScrollListener extends OnScrollListener {

    private static final int DEFAULT_VISIBLE_THRESOLD = 5;
    private final OnLoadMoreListener mMoreListener;
    private int mVisibleThreshold; // The minimum amount of items to have below your current scroll position before loading more.
    private int firstVisibleItem, lastVisibleItem, visibleItemCount, totalItemCount;

    /**
     * Listener for implementing lazy loading list (endless list)
     */
    public interface OnLoadMoreListener {
        /**
         * Callback for starting loading new portion of data
         */
        void onLoadMore();

        /**
         * Return loading status
         *
         * @return true if loading in progress
         */
        boolean isLoading();
    }

    /**
     * Creates ScrollListener for implementing lazy loading list (endless list)
     * If there are less then 5 items after last visible item in recycler view
     * and {@link OnLoadMoreListener#isLoading()} returns false,
     * then {@link OnLoadMoreListener#onLoadMore()} will be called.
     *
     * @param listener the callback {@link OnLoadMoreListener}
     */
    public LoadMoreScrollListener(@NonNull OnLoadMoreListener listener) {
        this(listener, DEFAULT_VISIBLE_THRESOLD);
    }

    /**
     * Creates ScrollListener for implementing lazy loading list (endless list)
     *
     * @param listener         the callback {@link OnLoadMoreListener}
     * @param visibleThreshold the amount of items. If there are less then visibleThreshold items
     *                         after last visible item in recycler view
     *                         and {@link OnLoadMoreListener#isLoading()} returns false,
     *                         then {@link OnLoadMoreListener#onLoadMore()} will be called.
     */
    public LoadMoreScrollListener(@NonNull OnLoadMoreListener listener, int visibleThreshold) {
        mMoreListener = listener;
        mVisibleThreshold = visibleThreshold;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if (dy < 0) return;

        final LayoutManager layoutManager = getLayoutManager(recyclerView);
        if (layoutManager == null) return;

        visibleItemCount = layoutManager.getChildCount();
        totalItemCount = layoutManager.getItemCount();
        firstVisibleItem = getFirstVisibleItemPosition(layoutManager);
        lastVisibleItem = firstVisibleItem + visibleItemCount - 1;

        if (!mMoreListener.isLoading()
                && lastVisibleItem >= (totalItemCount - mVisibleThreshold)) {
            // End has been reached
            mMoreListener.onLoadMore();
        }
    }

    private int getFirstVisibleItemPosition(LayoutManager layoutManager) {
        int pos = 0;
        if (layoutManager instanceof LinearLayoutManager) {
            pos = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            pos = ((StaggeredGridLayoutManager) layoutManager).findFirstVisibleItemPositions(null)[0];
        }
        return pos;
    }

    private LayoutManager getLayoutManager(RecyclerView recyclerView) {
        if (recyclerView != null) {
            return recyclerView.getLayoutManager();
        }
        return null;
    }
}