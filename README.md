# 嵌套滑动  
nested recyclerview

#滚动策略  
上滑：父view优先  
下滑：子view优先    

#bug  
暂不支持recyclerview1.1.0。recyclerview1.1.0实现了NestedScrollingChild3，并把dispatchNestedScroll方法设置为final方法，影响上滑时父view优先。
