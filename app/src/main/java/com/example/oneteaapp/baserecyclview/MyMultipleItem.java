package com.example.oneteaapp.baserecyclview;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.oneteaapp.base.HotProductBase;

import java.util.List;


/**
 * @author glsite.com
 * @version $Rev$
 * @des ${TODO}
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class MyMultipleItem implements MultiItemEntity {

    public static final int FIRST_TYPE = 1;//设置布局编号
    public static final int SECOND_TYPE = 2;
    public static final int NORMAL_TYPE = 3;
    public static final int HENAD_TYPE = 4;
    public static final int STERN_TYPE = 5;
    private int itemType;
    private List<HotProductBase> list;


    public MyMultipleItem(int itemType, List<HotProductBase> list) {
        this.itemType = itemType;
        this.list = list;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public List<HotProductBase>  getData(){
        return  list;
    }
}