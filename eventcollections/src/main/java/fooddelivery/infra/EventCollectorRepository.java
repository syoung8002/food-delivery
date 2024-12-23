package fooddelivery.infra;

import fooddelivery.domain.*;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(
    collectionResourceRel = "eventCollectors",
    path = "eventCollectors"
)
public interface EventCollectorRepository
    extends PagingAndSortingRepository<EventCollector, Long> {
    List<EventCollector> findByCorrelationKey(String correlationKey);

    @Query("SELECT e FROM EventCollector e WHERE e.timestamp >= :timestamp")
    List<EventCollector> findRecentEvents(@Param("timestamp") Long timestamp);

    @Query(
        "SELECT e FROM EventCollector e WHERE e.correlationKey = :correlationKey OR (:userId IS NOT NULL AND e.userId = :userId)"
    )
    List<EventCollector> findBySearchKey(
        @Param("correlationKey") String correlationKey,
        @Param("userId") String userId
    );
}
