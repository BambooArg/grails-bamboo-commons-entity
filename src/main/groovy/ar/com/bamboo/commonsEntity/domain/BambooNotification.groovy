package ar.com.bamboo.commonsEntity.domain

import ar.com.bamboo.framework.domains.BaseEntity
import groovy.transform.CompileStatic

/**
 * Created by orko on 04/09/14.
 */
@CompileStatic
abstract class BambooNotification extends BaseEntity{

    String body
    String subject


    @Override
    protected void executeMoreBeforeInsert() {

    }
}
