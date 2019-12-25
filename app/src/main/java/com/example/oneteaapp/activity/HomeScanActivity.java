package com.example.oneteaapp.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oneteaapp.R;
import com.example.oneteaapp.adapter.ScanAdapter;
import com.example.oneteaapp.adapter.ScanRecordAdapter;
import com.example.oneteaapp.view.flowlayout.FlowLayout;
import com.example.oneteaapp.view.flowlayout.TagAdapter;
import com.example.oneteaapp.view.flowlayout.TagFlowLayout;
import com.example.oneteaapp.view.flowlayout.db.RecordsDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 主页搜索
 */
public class HomeScanActivity extends BaseActivity implements ScanAdapter.MyClassifyAdapterOnItem, ScanRecordAdapter.ScanRecordAdapterAdapterOnItem {

    @BindView(R.id.ll_home_scan)
    LinearLayout llHomeScan;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tv_eliminate)
    TextView tvEliminate;
    @BindView(R.id.rv_scan2)
    RecyclerView rvScan2;
    List<String> list2;
    @BindView(R.id.fl_search_records)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.et_scan_text)
    EditText etScanText;
    @BindView(R.id.ll_scan_jilv)
    LinearLayout mHistoryContent;


    private RecordsDao mRecordsDao;
    //默然展示词条个数
    private final int DEFAULT_RECORD_NUMBER = 10;
    private List<String> recordList = new ArrayList<>();
    private TagAdapter mRecordsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_scan);
        ButterKnife.bind(this);
        //默认账号
        String username = "007";
        mRecordsDao = new RecordsDao(this, username);
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        list.add("");
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(HomeScanActivity.this);
        rvScan2.setLayoutManager(linearLayoutManager3);
        ScanAdapter classifyAdapter = new ScanAdapter(HomeScanActivity.this, list, this);
        rvScan2.setAdapter(classifyAdapter);

        initData();
        setOnClick();


    }

    private void setOnClick() {

        etScanText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                Toast.makeText(HomeScanActivity.this, etScanText.getText().toString(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        //创建历史标签适配器
        //为标签设置对应的内容
        mRecordsAdapter = new TagAdapter<String>(recordList) {

            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(HomeScanActivity.this).inflate(R.layout.tab_item,
                        tagFlowLayout, false);
                //为标签设置对应的内容
                tv.setText(s);
                return tv;
            }
        };
        tagFlowLayout.setAdapter(mRecordsAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public void onTagClick(View view, int position, FlowLayout parent) {
                //清空editText之前的数据
                etScanText.setText("");
                //将获取到的字符串传到搜索结果界面,点击后搜索对应条目内容
                etScanText.setText(recordList.get(position));
                etScanText.setSelection(etScanText.length());
            }
        });
        //删除某个条目
        tagFlowLayout.setOnLongClickListener(new TagFlowLayout.OnLongClickListener() {
            @Override
            public void onLongClick(View view, final int position) {
                showDialog("确定要删除该条历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //删除某一条记录
                        mRecordsDao.deleteRecord(recordList.get(position));
                    }
                });
            }
        });



//        //view加载完成时回调
//        tagFlowLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                boolean isOverFlow = tagFlowLayout.isOverFlow();
//                boolean isLimit = tagFlowLayout.isLimit();
//                if (isLimit && isOverFlow) {
//                    moreArrow.setVisibility(View.VISIBLE);
//                } else {
//                    moreArrow.setVisibility(View.GONE);
//                }
//            }
//        });


        //清除所有记录
        tvEliminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog("确定要删除全部历史记录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        tagFlowLayout.setLimit(true);
                        //清除所有数据
                        mRecordsDao.deleteUsernameAllRecords();
                    }
                });
            }
        });

        mRecordsDao.setNotifyDataChanged(new RecordsDao.NotifyDataChanged() {
            @Override
            public void notifyDataChanged() {
                initData();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String record = etScanText.getText().toString();
//                if (!TextUtils.isEmpty(record)) {
//                    //添加数据
//                    mRecordsDao.addRecords(record);
                finish();

            }
        });


    }

    private void showDialog(String dialogTitle, @NonNull DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeScanActivity.this);
        builder.setMessage(dialogTitle);
        builder.setPositiveButton("确定", onClickListener);
        builder.setNegativeButton("取消", null);
        builder.create().show();
    }

    private void initData() {
        Observable.create(new ObservableOnSubscribe<List<String>>() {
            @Override
            public void subscribe(ObservableEmitter<List<String>> emitter) throws Exception {
                emitter.onNext(mRecordsDao.getRecordsByNumber(DEFAULT_RECORD_NUMBER));
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(List<String> s) throws Exception {
                        recordList.clear();
                        recordList = s;
                        if (null == recordList || recordList.size() == 0) {
                            mHistoryContent.setVisibility(View.GONE);
                        } else {
                            mHistoryContent.setVisibility(View.VISIBLE);
                        }
                        if (mRecordsAdapter != null) {
                            mRecordsAdapter.setData(recordList);
                            mRecordsAdapter.notifyDataChanged();
                        }
                    }
                });
    }

    @Override
    public void OnItemClickListener(String name) {

    }

    @Override
    public void OnItemClickListenerScanRecordAdapter(String name) {
        //记录
    }


    @Override
    protected void onDestroy() {
        mRecordsDao.closeDatabase();
        mRecordsDao.removeNotifyDataChanged();
        super.onDestroy();
    }
}
