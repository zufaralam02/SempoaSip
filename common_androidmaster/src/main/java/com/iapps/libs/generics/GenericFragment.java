package com.iapps.libs.generics;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.iapps.common_library.R;
import com.iapps.libs.helpers.BaseConstants;
import com.iapps.libs.helpers.BaseHelper;
import com.iapps.libs.helpers.BaseUIHelper;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

public abstract class GenericFragment
        extends Fragment implements OnClickListener,
                                    OnItemClickListener,
                                    OnLongClickListener {
    private static final Field                        sChildFragmentManagerField;
    public               GenericFragment              parent;
    public               boolean                      isPhotoUpdate;
    private              BaseHelper.ImagePickListener imagePickListener;
    public               String                       title;

    // ================================================================================
    // Default functions
    // ================================================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int resLayout = setLayout();
        if (resLayout > 0) {
            View v = inflater.inflate(setLayout(), container, false);

            v.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return true;
                }
            });
            setHasOptionsMenu(true);
            
            return v;
        }
        
        return null;
    }
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        view.setBackgroundColor(getResources().getColor(R.color.background));

        if (isAdded()) {
            try {
                preSetView(view, savedInstanceState);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                setView(view, savedInstanceState);
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                postSetView(view, savedInstanceState);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        int resLayout = setMenuLayout();
        if (resLayout > 0)
            inflater.inflate(setMenuLayout(), menu);
        
        if (isDebugging() && refreshPage())
            inflater.inflate(R.menu.refresh_white, menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (setMenuLayout() == 0 && isDebugging() && item.getItemId() == R.id.menu_refresh) {
            onRefreshPage();
        }
        return super.onOptionsItemSelected(item);
    }
    
    public boolean refreshPage() {
        return false;
    }
    
    public void onRefreshPage() {
    }
    
    public abstract boolean isDebugging();
    
    public abstract int setLayout();
    
    public abstract int setMenuLayout();
    
    public abstract void setView(View view, Bundle savedInstanceState);
    
    public void preSetView(View view, Bundle savedInstanceState) {
        
    }
    
    public void postSetView(View view, Bundle savedInstanceState) {
        
    }

    public boolean showDefaultBackground() {
        return true;
    }
    
    /**
     * In fragment, you are not allowed to use non-empty constructors So use this functions below to get your
     * constructor
     *
     * @param obj
     *
     * @return
     */
    public GenericFragment param(Object obj) {
        return this;
    }
    
    public GenericFragment params(Object[] arrObj) {
        return this;
    }
    
    public GenericFragment title(String title) {
        return this;
    }
    
    public GenericFragment parent(GenericFragment parent) {
        this.parent = parent;
        return this;
    }
    
    public GenericFragment getParent() {
        return parent;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (this.parent != null)
            parent.onChildDestroyed();
    }
    
    public void onChildDestroyed() {
        
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseHelper.REQUEST_GET_IMAGE_CODE && resultCode == -1) {
            imagePickListener.onPickSuccess();
        } else {
            imagePickListener.onPickFail();
        }
    }
    
    public void setImagePickListener(BaseHelper.ImagePickListener listener) {
        this.imagePickListener = listener;
    }
    
    public void imagePickFail() {
        if (imagePickListener != null)
            imagePickListener.onPickFail();
    }
    
    // ================================================================================
    // Utilities
    // ================================================================================
    // To prevent error in implementing nested fragment
    static {
        Field f = null;
        try {
            f = Fragment.class.getDeclaredField("mChildFragmentManager");
            f.setAccessible(true);
        } catch (NoSuchFieldException e) {
            // Error getting mChildFragmentManager field
            e.printStackTrace();
        }
        sChildFragmentManagerField = f;
    }
    
    protected boolean isThere() {
        if (!getUserVisibleHint() || !isVisible() || !isAdded()) {
            return false;
        }
        
        return true;
    }
    
    public GenericActivity getHome() {
        return (GenericActivity) getActivity();
    }
    
    // ================================================================================
    // Title
    // ================================================================================
    public void setTitle(int resTitle) {
        getActivity().setTitle(resTitle);
    }
    
    public void setTitle(String title) {
        getActivity().setTitle(title);
    }
    
    // ================================================================================
    // Fragment
    // ================================================================================
    public void clearFragment() {
        if (getHome() != null)
            getHome().clearFragment();
    }
    
    public void setFragment(Fragment frag) {
        if (getHome() != null)
            getHome().setFragment(frag);
    }
    
    public void setFragment(Fragment frag, int resParent) {
        if (getHome() != null)
            getHome().setFragment(resParent, frag);
    }
    
    public void addFragment(Fragment frag) {
        if (getHome() != null)
            getHome().addFragment(frag);

        BaseUIHelper.hideKeyboard(getActivity());
    }

    public void addFragment(Fragment frag, String tag) {
        if (getHome() != null)
            getHome().addFragment(frag, tag);

        BaseUIHelper.hideKeyboard(getActivity());
    }

    @Override
    public void onDetach() {
        super.onDetach();
        
        if (sChildFragmentManagerField != null) {
            try {
                sChildFragmentManagerField.set(this, null);
            } catch (Exception e) {
                // Error setting mChildFragmentManager field
                e.printStackTrace();
            }
        }
    }
    
    // ================================================================================
    // Log
    // ================================================================================
    public void log(String text) {
        if (isDebugging())
            Log.d(BaseConstants.LOG, text);
    }
    
    // ================================================================================
    // Base Listener
    // ================================================================================
    @Override
    public boolean onLongClick(View v) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        
    }
    
    // ================================================================================
    // Pagination
    // ================================================================================
    public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
        private int visibleThreshold = 10;
        private int currentPage      = 1;
        private int mVisibleCount, mVisibleFirst;

        public EndlessScrollListener() {
        }
        
        public EndlessScrollListener(int visibleThreshold) {
            this.visibleThreshold = visibleThreshold;
        }
        
        public EndlessScrollListener(int visibleThreshold, int startPage) {
            this.visibleThreshold = visibleThreshold;
            this.currentPage = startPage;
        }
        
        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            this.mVisibleCount = visibleItemCount;
            this.mVisibleFirst = firstVisibleItem;
        }
        
        public abstract boolean onLoadMore(int page);
        
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            if (scrollState == SCROLL_STATE_IDLE && visibleThreshold * currentPage <= mVisibleCount + mVisibleFirst) {
                currentPage++;
                onLoadMore(currentPage);
            }
        }
    }

    // Untested
    public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        private int     visibleThreshold       = 30;
        private int     currentPage            = 0;
        private int     previousTotalItemCount = 0;
        private boolean loading                = true;
        private int     startingPageIndex      = 0;
        RecyclerView.LayoutManager mLayoutManager;

        public void reset() {
            visibleThreshold = 30;
            currentPage = 0;
            previousTotalItemCount = 0;
            loading = true;
            startingPageIndex = 0;
        }

        public EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
            this.mLayoutManager = layoutManager;
        }

        public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager) {
            this.mLayoutManager = layoutManager;
            visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
        }

        public EndlessRecyclerViewScrollListener(StaggeredGridLayoutManager layoutManager) {
            this.mLayoutManager = layoutManager;
            visibleThreshold = visibleThreshold * layoutManager.getSpanCount();
        }

        public int getLastVisibleItem(int[] lastVisibleItemPositions) {
            int maxSize = 0;
            for (int i = 0; i < lastVisibleItemPositions.length; i++) {
                if (i == 0) {
                    maxSize = lastVisibleItemPositions[i];
                } else if (lastVisibleItemPositions[i] > maxSize) {
                    maxSize = lastVisibleItemPositions[i];
                }
            }
            return maxSize;
        }

        @Override
        public void onScrolled(RecyclerView view, int dx, int dy) {
            int lastVisibleItemPosition = 0;
            int totalItemCount          = mLayoutManager.getItemCount();

            if (mLayoutManager instanceof StaggeredGridLayoutManager) {
                int[] lastVisibleItemPositions = ((StaggeredGridLayoutManager) mLayoutManager).findLastVisibleItemPositions(
                        null);
                lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
            } else if (mLayoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            } else if (mLayoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
            }

            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPageIndex;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }

            if (loading && (totalItemCount > previousTotalItemCount)) {
                loading = false;
                previousTotalItemCount = totalItemCount;
            }

            if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
                currentPage++;
                onLoadMore(currentPage);
                loading = true;
            }
        }

        public abstract void onLoadMore(int page);

    }
}
