package dalian.razvan.cucer.core.baseClasses

interface RecyclerViewItemClickListener<T: BaseModel> {

    fun onItemClick(item: T)
}