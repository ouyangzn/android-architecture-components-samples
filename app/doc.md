# _Lifecycle_ 简介
**lifecycle**是android官方推出的组件,目前还在alpha测试阶段

主要用于更方便的管理`activity/fragment`生命周期、数据加载等

其library中主要包含三大模块: 
- `Lifecycle` 生命周期相关
- `LiveData` 数据管理相关
- `ViewModel` View与model的连接桥

## _Lifecycle_ 
### 简介
使用`Lifecycle`可以更容易对`activity/fragment`的生命周期进行监控,
在各个生命周期做对应的处理,有效防止内存泄漏等问题


### 使用方式
- _step1_ 引入library
```groove
compile "android.arch.lifecycle:extensions:1.0.0-alpha7"
annotationProcessor "android.arch.lifecycle:compiler:1.0.0-alpha7"
```

- _step2_ 代码引用,分两种方式
  - 继承`LifecycleActivity/LifecycleFragment`
  ```java
  public class LifecycleActivity extends android.arch.lifecycle.LifecycleActivity {
    // ...
  }
  ```
  
  - 实现`LifecycleRegistryOwner`接口
  ```java
  public class LifecycleActivity extends android.app.Activity implements LifecycleRegistryOwner {
  
    protected final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
  
    @Override public LifecycleRegistry getLifecycle() {
      return mRegistry;
    }
  }
  ```
  
- _step3_ 定义生命周期的观察类,并注册观察
```java
/**
* 实现生命周期观察
*/
public class LocationManager implements LifecycleObserver {
  @OnLifecycleEvent(Lifecycle.Event.ON_RESUME) void onStartLocate() {
    // do on activity/fragment onResume()
  }

  @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE) void onStopLocate() {
    // do on activity/fragment onPause()
  }
}
```

```java
public class LifecycleActivity implements LifecycleRegistryOwner {
  
    protected final LifecycleRegistry mRegistry = new LifecycleRegistry(this);
    private LocationManager mLocationManager;
    
    public void onCreate() {
      // 注册观察类
      mRegistry.addObserver(mLocationManager);
    }
  
    @Override public LifecycleRegistry getLifecycle() {
      return mRegistry;
    }
  }

```

## _LiveData_ 
### 简介
`LiveData`用于提供一个动态的数据管理方式,可以根据是否有对象观察数据的变化来进行对应的操作

比如无观察者时停止数据的变化,等有观察者时再开启变化

### 模块使用方式
- _step1_ 引入library
```groove
compile "android.arch.lifecycle:extensions:1.0.0-alpha7"
annotationProcessor "android.arch.lifecycle:compiler:1.0.0-alpha7"
```

- _step2_ 定义数据类继承LiveData类
```java
public class DataLiveData extends LiveData<TestData> {
  // ...
}
```

- _step3_ 重写需要的方法,一般只需要重写`onActive()`和`onInactive()`方法
```java
public class DataLiveData extends LiveData<TestData> {
  private Handler mHandler;
  private Runnable mDataRunnable;

  public DataLiveData() {
    mHandler = new Handler();
    mDataRunnable = new Runnable() {
      @Override public void run() {
        Random random = new Random();
        // 修改数据
        postValue(new TestData("随机数:" + random.nextInt()));
        mHandler.postDelayed(this, 2000);
      }
    };
  }
  
  @Override protected void onActive() {
    Log.d(TAG, "---------onActive--------");
    // 有观察者,开启数据操作
    mHandler.post(mDataRunnable);
  }
  
  @Override protected void onInactive() {
    Log.d(TAG, "---------onInactive--------");
    // 没人观察变化,取消对数据的操作
    mHandler.removeCallbacks(mDataRunnable);
  }
}
```

```java
public class TestLiveDataActivity extends BaseActivity implements Observer<TestData> {

  @BindView(R.id.tv_test_live_data) TextView mTvData;
  private DataLiveData mDataLiveData;

  @Override protected int getContentView() {
    return R.layout.activity_test_live_data;
  }

  @Override protected void initData() {
    mDataLiveData = new DataLiveData();
  }

  @Override protected void initView() {

  }

  @Override protected void onResume() {
    super.onResume();
    // onResume时观察数据变化情况
    mDataLiveData.observeForever(this);
  }

  // onPause时取消观察数据变化
  @Override protected void onPause() {
    super.onPause();
    mDataLiveData.removeObserver(this);
  }

  // call on data changed
  @Override public void onChanged(@Nullable TestData data) {
    mTvData.setText(data != null ? data.getContent() : "无数据");
  }
}
```

## _ViewModel_
### 简介
`ViewModel`是分层开发时连接View层与model层的一个桥梁,用于给 _View_ 层提供唯一数据

`ViewModel`理论上是唯一的,只有一个实例存在,因此可以保证不同 _View_ 中获取到的数据都是相同的

### 使用方法
- _step1_ 引入library
```groove
compile "android.arch.lifecycle:extensions:1.0.0-alpha7"
annotationProcessor "android.arch.lifecycle:compiler:1.0.0-alpha7"
```

- _step2_ 定义数据提供类继承ViewModel类
```java
public class ItemDataListViewModel extends ViewModel {
  // ...
}
```

- _step3_ 在 _View_ 中使用
```java
public class TestViewModelActivity extends BaseLifecycleActivity {

  private ItemDataListViewModel mListViewModel;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    // 获取ViewModel以使用数据
    mListViewModel = ViewModelProviders.of(this).get(ItemDataListViewModel.class);
  }
}
```


