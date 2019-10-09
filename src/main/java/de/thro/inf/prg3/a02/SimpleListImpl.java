package de.thro.inf.prg3.a02;

import com.sun.org.apache.xalan.internal.xsltc.dom.SimpleResultTreeImpl;
import org.w3c.dom.html.HTMLDListElement;

import java.util.Iterator;

/**
 * @author Peter Kurfer
 * Created on 10/6/17.
 */
public class SimpleListImpl implements SimpleList,Iterable<Object> {
    private ListElement head;
    private int size;


    public SimpleListImpl(){
        head=null;
    }

    @Override
    public void add(Object o) {
        if(head==null){
            head=new ListElement(o);
        }else {
            ListElement current=head;
            while (current.getNext() != null){
                current=current.getNext();
            }
            current.setNext(new ListElement(o));
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public SimpleList filter(SimpleFilter filter) {
        SimpleList result=new SimpleListImpl();
        for(Object o : this){
            if(filter.include(o)){
                result.add(o);
            }

        }
        return  result;
    }

    @Override
    public Iterator<Object> iterator() {
        return new SimpleIterator();
    }

    private class SimpleIterator implements Iterator<Object>{
        private ListElement current=head;


        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            Object tmp=current.getItem();
            current=current.getNext();
            return tmp;
        }
    }

    private static class ListElement {
        private Object item;
        private ListElement next;

        public ListElement(Object item) {
            this.item = item;
            this.next = null;
        }

        public Object getItem() {
            return item;
        }

        /**
         * @return successor of the ListElement - may be NULL
         */
        public ListElement getNext() {
            return next;
        }

        /**
         * Sets the successor of the ListElement
         *
         * @param next ListElement
         */
        public void setNext(ListElement next) {
            this.next = next;
        }

    }
}
