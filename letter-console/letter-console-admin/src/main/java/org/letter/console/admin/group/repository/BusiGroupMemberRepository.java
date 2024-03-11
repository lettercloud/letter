package org.letter.console.admin.group.repository;

import org.letter.console.admin.group.domain.BusiGroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * BusiGroupMemberRepository
 *
 * @author letter
 */
public interface BusiGroupMemberRepository extends JpaRepository<BusiGroupMember, Long>, JpaSpecificationExecutor<BusiGroupMember> {

}
