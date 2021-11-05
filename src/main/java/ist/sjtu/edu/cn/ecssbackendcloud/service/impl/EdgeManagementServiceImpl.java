package ist.sjtu.edu.cn.ecssbackendcloud.service.impl;

import ist.sjtu.edu.cn.ecssbackendcloud.dto.EdgeInfoDto;
import ist.sjtu.edu.cn.ecssbackendcloud.service.EdgeManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Service
@Slf4j
public class EdgeManagementServiceImpl implements EdgeManagementService {

    @Override
    public List<EdgeInfoDto> getAllEdgeInfo() {
        return new List<EdgeInfoDto>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<EdgeInfoDto> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(EdgeInfoDto edgeInfoDto) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends EdgeInfoDto> c) {
                return false;
            }

            @Override
            public boolean addAll(int index, Collection<? extends EdgeInfoDto> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public EdgeInfoDto get(int index) {
                return null;
            }

            @Override
            public EdgeInfoDto set(int index, EdgeInfoDto element) {
                return null;
            }

            @Override
            public void add(int index, EdgeInfoDto element) {

            }

            @Override
            public EdgeInfoDto remove(int index) {
                return null;
            }

            @Override
            public int indexOf(Object o) {
                return 0;
            }

            @Override
            public int lastIndexOf(Object o) {
                return 0;
            }

            @Override
            public ListIterator<EdgeInfoDto> listIterator() {
                return null;
            }

            @Override
            public ListIterator<EdgeInfoDto> listIterator(int index) {
                return null;
            }

            @Override
            public List<EdgeInfoDto> subList(int fromIndex, int toIndex) {
                return null;
            }
        };
    }

    @Override
    public EdgeInfoDto getEdgeInfoById(String EdgeId) {
        return new EdgeInfoDto();
    }

    @Override
    public EdgeInfoDto addEdge(EdgeInfoDto edgeInfoDto) {
        return new EdgeInfoDto();
    }
}
