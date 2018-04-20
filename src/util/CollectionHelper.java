package util;

import java.util.Collection;

/**
 * Created by STGLJY@126.COM on 2017/12/20.
 */
public class CollectionHelper<E> {//本类可以接受泛型参数
    private static CollectionHelper collectionHelper = new CollectionHelper();
    public static CollectionHelper getInstance(){
        return CollectionHelper.collectionHelper;
    }

    /**
     * 本方法常用于数据库测试场合下，
     * A类关联B时，创建A类对象需要事先得到一个B类对象
     * 由于事先不知道哪个id对应的记录是存在的，故获得所有对象后再抽取其中一个
     * 本方法可以得到第一个记录对应的对象
     * 在泛型的支持下，可以操作任何类型的集合类对象
     * 需要在创建本类对象时指定元素类型，
     * new CollectionHelper<School>().getFirstFromCollection(schools);
     * 上语句表示：要取出以School为元素类型的集合类对象schools中的第一个元素
     * new CollectionHelper<Department>().getFirstFromCollection(departments);
     * 上语句表示：要取出以Department为元素类型的集合类对象departments中的第一个元素
     * @param collection 以泛型对象为元素的集合
     * @return 泛型对象元素
     */
    public E getFirstFromCollection(Collection<E> collection){
        E firstElement = null;
        for(E e:collection){
            firstElement = e;
            break;
        }
        return firstElement;
    }

    public E getSecondFromCollection(Collection<E> collection){
        int counter = 1;
        E secondElement = null;
        for(E e:collection){
            secondElement = e;
            if(counter==2){
                break;
            }
            counter++;
        }
        return secondElement;
    }

}
