package org.letter.console.admin.group.repository;

import org.letter.console.admin.group.domain.BusiGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * BusiGroupRepository
 *
 * @author letter
 */
public interface BusiGroupRepository extends JpaRepository<BusiGroup, Long>, JpaSpecificationExecutor<BusiGroup> {
	
}
