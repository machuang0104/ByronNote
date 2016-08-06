package com.byron.library.swipe.interfaces;

import com.byron.library.swipe.SwipeLayout;
import com.byron.library.swipe.Mode;

import java.util.List;


public interface SwipeItemMangerInterface {

    public void openItem(int position);

    public void closeItem(int position);

    public void closeAllExcept(SwipeLayout layout);

    public List<Integer> getOpenItems();

    public List<SwipeLayout> getOpenLayouts();

    public void removeShownLayouts(SwipeLayout layout);

    public boolean isOpen(int position);

    public Mode getMode();

    public void setMode(Mode mode);
}
