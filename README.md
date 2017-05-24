# LishyLibrary
ObserveRecyclerView 
setEmptyView for RecyclerView

How to Use




  View emptyView = LayoutInflater.from(context).inflate(R.layout.emptyView,null);

  ObserveRecyclerView recyclerView = (ObserveRecyclerView)findViewById(R.id.recyclerView);
  recyclerView.setEmptyView(emptyView);
  
  
